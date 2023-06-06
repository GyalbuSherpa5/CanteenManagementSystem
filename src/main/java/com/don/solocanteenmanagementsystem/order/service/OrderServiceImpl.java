package com.don.solocanteenmanagementsystem.order.service;

import com.don.solocanteenmanagementsystem.category.exception.CategoryDoNotExistException;
import com.don.solocanteenmanagementsystem.category.model.FoodCategory;
import com.don.solocanteenmanagementsystem.category.repository.FoodCategoryRepository;
import com.don.solocanteenmanagementsystem.customer.exception.CustomerDoNotExistException;
import com.don.solocanteenmanagementsystem.customer.model.Customer;
import com.don.solocanteenmanagementsystem.customer.repository.CustomerRepository;
import com.don.solocanteenmanagementsystem.fooditem.dto.FoodDtoForOrder;
import com.don.solocanteenmanagementsystem.fooditem.dto.FoodDtoForResponseOrder;
import com.don.solocanteenmanagementsystem.fooditem.enums.FoodStatus;
import com.don.solocanteenmanagementsystem.fooditem.exception.FoodItemDoNotExistException;
import com.don.solocanteenmanagementsystem.fooditem.model.FoodItem;
import com.don.solocanteenmanagementsystem.fooditem.model.StoreFoodQuantity;
import com.don.solocanteenmanagementsystem.fooditem.repository.FoodItemRepository;
import com.don.solocanteenmanagementsystem.fooditem.repository.StoreFoodQuantityRepository;
import com.don.solocanteenmanagementsystem.order.dto.RequestOrderDto;
import com.don.solocanteenmanagementsystem.order.dto.ResponseOrderDto;
import com.don.solocanteenmanagementsystem.order.exception.OrderIdNotFoundException;
import com.don.solocanteenmanagementsystem.order.exception.OutOfStockException;
import com.don.solocanteenmanagementsystem.order.model.Order;
import com.don.solocanteenmanagementsystem.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final FoodItemRepository foodItemRepository;
    private final CustomerRepository customerRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final StoreFoodQuantityRepository foodQuantityRepository;
    private long token = 0;

    public long getLastToken() {
        List<Long> tokens = foodQuantityRepository.findAllToken();
        if (tokens.isEmpty()) {
            return 0;
        }
        return tokens.get(tokens.size() - 1);
    }

    @Override
    public void placeOrder(RequestOrderDto requestOrderDto) {

        token = getLastToken();
        Order order = new Order();

        Optional<Customer> customer = customerRepository.findById(requestOrderDto.getCustomerId());
        if (customer.isPresent()) {
            order.setCustomer(customer.get());
        } else {
            throw new CustomerDoNotExistException("This customer doesn't exist");
        }

        List<FoodItem> foodItems = new ArrayList<>();

        List<FoodDtoForOrder> foodDto = requestOrderDto.getFoodItems();

        double totalPrice = 0;

        for (FoodDtoForOrder foods : foodDto) {
            Optional<FoodItem> foodItemsFromRepo = foodItemRepository.findFoodItemByName(foods.getFoodName());
            if (!foodItemsFromRepo.isPresent()) {
                throw new FoodItemDoNotExistException("Food item with name " + foods.getFoodName() + " do not exist");
            }

            Optional<FoodCategory> foodCategory =
                    foodCategoryRepository.findFoodCategoryByName(foods.getFoodCategoryName());
            if (!foodCategory.isPresent()) {
                throw new CategoryDoNotExistException("Food category with name " + foods.getFoodCategoryName() + " do" +
                        " not exist");
            }

            List<FoodItem> foodItemsOfCategory = foodCategory.get().getFoodItems();

            List<String> foodItemsFromCategory = new ArrayList<>();

            for (FoodItem foodItem : foodItemsOfCategory) {
                String name = foodItem.getName();
                foodItemsFromCategory.add(name);
            }

            if (!foodItemsFromCategory.contains(foods.getFoodName())) {
                throw new FoodItemDoNotExistException("Food item with name " + foods.getFoodName() + " do not exist " +
                        "in this category");
            }

            int stock = foodItemsFromRepo.get().getStock();

            if (foods.getFoodQuantity() > stock || foodItemsFromRepo.get().getAvailability() == FoodStatus.NOT_AVAILABLE) {
                throw new OutOfStockException(stock + " quantity available for " + foods.getFoodName());
            } else {
                stock = stock - foods.getFoodQuantity();
                if (stock == 0) {
                    foodItemsFromRepo.get().setAvailability(FoodStatus.NOT_AVAILABLE);
                }
                foodItemsFromRepo.get().setStock(stock);
                foodItemRepository.save(foodItemsFromRepo.get());
            }

            totalPrice += foodItemsFromRepo.get().getPrice() * foods.getFoodQuantity();

            token += 1;
            StoreFoodQuantity foodQuantity = new StoreFoodQuantity();
            foodQuantity.setQuantity(foods.getFoodQuantity());
            foodQuantity.setCustomerId(order.getCustomer().getCustomerId());
            foodQuantity.setFoodId(foodItemsFromRepo.get().getFoodId());
            foodQuantity.setFoodCategoryName(foods.getFoodCategoryName());
            foodQuantity.setToken(token);

            foodQuantityRepository.save(foodQuantity);
            token -= 1;

            foodItems.add(foodItemsFromRepo.get());
        }
        token += 1;

        order.setTotalPrice(totalPrice);
        order.setFoodItems(foodItems);
        order.setQuantity(foodItems.size());

        orderRepository.save(order);
    }

    @Override
    public List<ResponseOrderDto> getAllOrders() {

        List<Order> orders = orderRepository.findAll();

        List<ResponseOrderDto> responseOrderDtoArrayList = new ArrayList<>();

        for (Order order : orders) {

            ResponseOrderDto responseOrderDto = new ResponseOrderDto();

            responseOrderDto.setTotalPrice(order.getTotalPrice());
            responseOrderDto.setQuantity(order.getQuantity());
            responseOrderDto.setCustomerId(order.getCustomer().getCustomerId());

            List<FoodDtoForResponseOrder> foodDtoForOrderList = new ArrayList<>();

            List<FoodItem> items = order.getFoodItems();

            for (FoodItem item : items) {
                FoodDtoForResponseOrder foodDtoForOrder = new FoodDtoForResponseOrder();
                foodDtoForOrder.setFoodName(item.getName());
                long quantity = foodQuantityRepository.findQuantityByFourIds(item.getFoodId(),
                        order.getCustomer().getCustomerId(), item.getFoodCategory().getName(),
                        order.getOrderId());
                foodDtoForOrder.setQuantity(quantity);


                foodDtoForOrderList.add(foodDtoForOrder);
            }

            responseOrderDto.setFoodItems(foodDtoForOrderList);
            responseOrderDtoArrayList.add(responseOrderDto);
        }

        return responseOrderDtoArrayList;
    }

    @Override
    public ResponseOrderDto getOrderById(Long id) {

        Order order = orderRepository.findById(id).orElseThrow(
                () -> new OrderIdNotFoundException("This order id doesn't exist")
        );

        ResponseOrderDto dto = new ResponseOrderDto();

        dto.setTotalPrice(order.getTotalPrice());
        dto.setQuantity(order.getQuantity());
        dto.setCustomerId(order.getCustomer().getCustomerId());

        List<FoodItem> foodDtoForOrderList = order.getFoodItems();
        List<FoodDtoForResponseOrder> orders = new ArrayList<>();

        for (FoodItem foodItem : foodDtoForOrderList) {
            FoodDtoForResponseOrder foodDtoForOrder = new FoodDtoForResponseOrder();
            foodDtoForOrder.setFoodName(foodItem.getName());

            long quantity = foodQuantityRepository.findQuantityByFourIds(foodItem.getFoodId(),
                    order.getCustomer().getCustomerId(), foodItem.getFoodCategory().getName(), id);
            foodDtoForOrder.setQuantity(quantity);

            orders.add(foodDtoForOrder);
        }
        dto.setFoodItems(orders);

        return dto;
    }
}

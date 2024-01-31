package gov.in.oupp.training.java.advancejava.mvc.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gov.in.oupp.training.java.advancejava.mvc.models.Restaurant;

public class RestaurantDaoImpl implements RestaurantDao {
	private List<Restaurant> restaurantList = new ArrayList<>();
	private int nextId = 1; // Next available ID for new restaurants

	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantList;
	}

	@Override
	public Restaurant getRestaurantById(int id) {
		for (Restaurant restaurant : restaurantList) {
			if (restaurant.getId() == id) {
				return restaurant;
			}
		}
		return null;
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {
		restaurant.setId(nextId++); // Assign the next available ID
		restaurantList.add(restaurant);
	}

	@Override
	public void updateRestaurant(Restaurant updatedRestaurant) {
		for (Restaurant restaurant : restaurantList) {
			if (restaurant.getId() == updatedRestaurant.getId()) {
				restaurant.setName(updatedRestaurant.getName());
				restaurant.setAddress(updatedRestaurant.getAddress());
				// Update other fields if needed
				break;
			}
		}
	}

	@Override
	public void deleteRestaurant(int id) {
		Iterator<Restaurant> iterator = restaurantList.iterator();
		while (iterator.hasNext()) {
			Restaurant restaurant = iterator.next();
			if (restaurant.getId() == id) {
				iterator.remove();
				break;
			}
		}
	}
}

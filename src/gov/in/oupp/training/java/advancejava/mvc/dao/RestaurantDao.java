package gov.in.oupp.training.java.advancejava.mvc.dao;

import java.util.List;

import gov.in.oupp.training.java.advancejava.mvc.models.Restaurant;

public interface RestaurantDao {
	List<Restaurant> getAllRestaurants();

	Restaurant getRestaurantById(int id);

	void addRestaurant(Restaurant restaurant);

	void updateRestaurant(Restaurant restaurant);

	void deleteRestaurant(int id);
}

package com.company.motoedge.viewModels;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class MotorcycleViewModel {
	private Integer id;
	@Digits(integer = 7, fraction = 2)
	@NotNull
	private BigDecimal price;
	@NotBlank
	@Size(min = 1, max = 20)
	private String vin;
	@NotBlank
	@Size(min = 1, max = 20)
	private String make;
	@NotBlank
	@Size(min = 1, max = 20)
	private String model;
	@NotBlank
	@Size(min = 4, max = 4)
	private String year;
	@NotBlank
	@Size(min = 1, max = 20)
	private String color;

	public MotorcycleViewModel() {
	}

	public MotorcycleViewModel(BigDecimal price, String vin, String make, String model, String year, String color) {
		this.price = price;
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
	}

	public MotorcycleViewModel(Integer id, BigDecimal price, String vin, String make, String model, String year, String color) {
		this.id = id;
		this.price = price;
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MotorcycleViewModel that = (MotorcycleViewModel) o;
		return id.equals(that.id) &&
			price.equals(that.price) &&
			vin.equals(that.vin) &&
			make.equals(that.make) &&
			model.equals(that.model) &&
			year.equals(that.year) &&
			color.equals(that.color);
	}

	@Override
	public int hashCode() {
		return Objects.hash(price, vin, make, model, year, color);
	}

	@Override
	public String toString() {
		return "Motorcycle{" +
			"id=" + id +
			", price=" + price +
			", vin='" + vin + '\'' +
			", make='" + make + '\'' +
			", model='" + model + '\'' +
			", year='" + year + '\'' +
			", color='" + color + '\'' +
			'}';
	}
}

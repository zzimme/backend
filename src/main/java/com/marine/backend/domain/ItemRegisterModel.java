package com.marine.backend.domain;

import javax.validation.constraints.NotNull;

public class ItemRegisterModel {
		
		private Integer id;
		@NotNull
		private String name;
		@NotNull
		private Float lat;
		@NotNull
		private Float lng;
		@NotNull
		private Integer rent;
		@NotNull
		private Integer deposit;
		@NotNull
		private String description;
		@NotNull
		private Integer type;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Float getLat() {
			return lat;
		}

		public void setLat(Float lat) {
			this.lat = lat;
		}

		public Float getLng() {
			return lng;
		}

		public void setLng(Float lng) {
			this.lng = lng;
		}

		public Integer getRent() {
			return rent;
		}

		public void setRent(Integer rent) {
			this.rent = rent;
		}

		public Integer getDeposit() {
			return deposit;
		}

		public void setDeposit(Integer deposit) {
			this.deposit = deposit;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}
		
		
}

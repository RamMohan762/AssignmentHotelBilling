package properties;

public class Hotel {
	
		
		private Integer id;
		private String pname;
		private Double price;
		
		public Hotel(Integer id, String pname, Double price) {
			super();
			this.id = id;
			this.pname = pname;
			this.price = price;
		}

		public Hotel(String pname, Double price) {
			super();
			this.pname = pname;
			this.price = price;
		}
		
		public Hotel() {
			super();
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getPname() {
			return pname;
		}

		public void setPname(String pname) {
			this.pname = pname;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}
		

}

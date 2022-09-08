package model;

public class ItemVO {
	private long id;
	private String name;
	private String maker;
	private long price;
	public ItemVO() {
	}
	public ItemVO(long id, String name, String maker, long price) {
		this.id = id;
		this.name = name;
		this.maker = maker;
		this.price = price;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ItemVO [id=" + id + ", name=" + name + ", maker=" + maker + ", price=" + price + "]";
	}
}

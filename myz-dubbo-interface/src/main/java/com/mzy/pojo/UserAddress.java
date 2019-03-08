package com.mzy.pojo;

//因为rpc底层对象的传输是通过序列化进行传输，那么每个实体类都必须实现Serializable
public class UserAddress implements java.io.Serializable {

	private Long id;
	private String address;

	public UserAddress() {
		super();
	}

	public UserAddress(Long id, String address) {
		super();
		this.id = id;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserAddress [id=" + id + ", address=" + address + "]";
	}

}

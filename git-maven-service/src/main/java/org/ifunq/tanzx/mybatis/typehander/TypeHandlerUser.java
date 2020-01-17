package org.ifunq.tanzx.mybatis.typehander;

public class TypeHandlerUser {
    private Integer id;
    private String name;
    private String phone;
    private TypeHandlerUserAddress address;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public TypeHandlerUserAddress getAddress() {
        return address;
    }

    public void setAddress(TypeHandlerUserAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "TypeHandlerUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                '}';
    }
}

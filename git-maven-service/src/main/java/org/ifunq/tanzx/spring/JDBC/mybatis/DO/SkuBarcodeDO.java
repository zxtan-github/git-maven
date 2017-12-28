package org.ifunq.tanzx.spring.JDBC.mybatis.DO;

/**
 * 基础商品条码DO
 *
 * @author tanzx
 * @create 2017-07-12 18:22
 **/
public class SkuBarcodeDO  {

    private static final long serialVersionUID = -4498478048292642391L;

    /**
     * id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 商品条码
     */
    private String barcode;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改时间
     */
    private String gmtModified;

    /**
     * 修改人
     */
    private String modifiedBy;

    /**
     * 删除标识
     */
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "SkuBarcodeDO{" +
                "id=" + id +
                ", skuId='" + skuId + '\'' +
                ", barcode='" + barcode + '\'' +
                '}';
    }
}

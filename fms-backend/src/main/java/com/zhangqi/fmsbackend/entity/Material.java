package com.zhangqi.fmsbackend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
@TableName("t_material")
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 原料编号
     */
    private Integer materialId;

    /**
     * 原料类型
     */
    private String materialType;

    /**
     * 原料价格
     */
    private BigDecimal materialPrice;

    /**
     * 原料库存
     */
    private Integer materialInventory;

    /**
     * 原料供应商
     */
    private String materialSupply;

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }
    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }
    public BigDecimal getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(BigDecimal materialPrice) {
        this.materialPrice = materialPrice;
    }
    public Integer getMaterialInventory() {
        return materialInventory;
    }

    public void setMaterialInventory(Integer materialInventory) {
        this.materialInventory = materialInventory;
    }
    public String getMaterialSupply() {
        return materialSupply;
    }

    public void setMaterialSupply(String materialSupply) {
        this.materialSupply = materialSupply;
    }

    @Override
    public String toString() {
        return "Material{" +
            "materialId=" + materialId +
            ", materialType=" + materialType +
            ", materialPrice=" + materialPrice +
            ", materialInventory=" + materialInventory +
            ", materialSupply=" + materialSupply +
        "}";
    }
}

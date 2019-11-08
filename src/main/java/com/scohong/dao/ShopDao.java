package com.scohong.dao;

import com.scohong.entity.junengchi.Shop;
import com.scohong.entity.map.ShopMap;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShopDao {

    @Select("select id shopId,`name` shopName,city,address,introduction,cover_pic,thumb_cover_pic " +
            "from shop ")
    List<Shop> getAllShops();

    @Insert("INSERT into shop(`name`,introduction,city,address,cover_pic) values (#{shopName},#{introduction} ,#{city}, " +
            "#{address},#{coverPic}  )")
    boolean addShop(Shop shop);

    @Delete("delete from shop where id =#{shopId}")
    boolean delShop(int shopId);

    @Select("select id  from shop where `name` = #{shopName}")
    Integer getShopIdByName(String name);

    @Select("select id shopId,`name` shopName,city,address,introduction,cover_pic,thumb_cover_pic " +
            "from shop where `name` = #{shopName}")
    Shop getShopByName(String name);

    @Update("update shop set `name` = #{shopName},city = #{city},introduction = #{introduction}," +
            "address = #{address} where id = #{shopId} ")
    Integer updateShop(Shop shop);

    @Select("select id shopId,`name` shopName,city,address,introduction,cover_pic,thumb_cover_pic " +
            "from shop where `name` like CONCAT('%',#{name},'%')")
    List<Shop> searchShopByName(String name);

    @Update("update shop set cover_pic = #{coverPic} where `name` = #{shopName} ")
    Integer updateShopCoverPic(String shopName,String coverPic);

    @Update("update shop set cover_pic_md5 = #{coverPic} where `name` = #{shopName} ")
    Integer updateShopCoverPicMd5(String shopName,String coverPic);

    /**
     * 地图更新
     * @return
     */
    @Select("select id,address,latitude,longitude,city from shop where id >= #{id}")
    List<ShopMap> getAllShopByMap(int id);

    @Update("UPDATE shop set longitude = #{longitude},latitude = #{latitude} ,`level` = #{level}  where id = #{id} ")
    int updateAddress(ShopMap frame);
}

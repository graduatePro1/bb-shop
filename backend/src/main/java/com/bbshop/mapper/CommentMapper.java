package com.bbshop.mapper;

import com.bbshop.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作comment相关数据接口
*/
@Mapper
public interface CommentMapper {

    /**
      * 新增
    */
    int insert(Comment comment);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Comment comment);

    /**
      * 根据ID查询
    */
    Comment selectById(Integer id);

    /**
      * 查询所有
    */
    List<Comment> selectAll(Comment comment);

    @Select("select comment.*, user.Photo as userPhoto, user.name as userName from comment left join user on comment.user_id = user.id where comment.goods_id = #{id}")
    List<Comment> selectByGoodsId(Integer id);
}
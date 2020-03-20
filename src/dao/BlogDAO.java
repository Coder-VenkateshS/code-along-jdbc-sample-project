package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

public class BlogDAO {
	static List<Blog> blogList= new ArrayList<Blog>(); 
	
	final String INSERT_BLOG_QUERY = "INSERT INTO blog (blogId, blogTitle, blogDescription, postedOn) VALUES (seq_blog.nextval, ?, ?, ?)";
	final String SELECT_ALL_BLOGS = "Select * from BLOG";
	public void insertBlog(Blog blog) throws SQLException, Exception {
		PreparedStatement ps=ConnectionManager.getConnection().prepareStatement(INSERT_BLOG_QUERY);
		ps.setString(1, blog.getBlogTitle());
		ps.setString(2, blog.getBlogDescription());
		ps.setDate(3,java.sql.Date.valueOf(blog.getPostedOn()));
		ps.executeUpdate();
		}

	public List<Blog> getAllBlogs() throws SQLException, Exception {
		
		Blog blog = new Blog();
		
		
		PreparedStatement ps=ConnectionManager.getConnection().prepareStatement(SELECT_ALL_BLOGS);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			int blogId = rs.getInt("blogId");
			String blogTitle = rs.getString("blogTitle");
			String BlogDescription = rs.getString("blogDescription");
			LocalDate postedOn = rs.getDate("postedOn").toLocalDate();
			System.out.println(blogId);
			System.out.println(blogTitle);
			System.out.println(BlogDescription);
			System.out.println(postedOn);
			
			blog.setBlogId(blogId);
			blog.setBlogTitle(blogTitle);
			blog.setBlogDescription(BlogDescription);
			blog.setPostedOn(postedOn);
			
			blogList.add(blog);
		}
		
		
		return blogList;
	}
	
	
	
}

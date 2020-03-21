package dao;

import java.sql.Connection;
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
	
	// Queries to work with database tables
	final String INSERT_BLOG_QUERY = "INSERT INTO blog (blogId, blogTitle, blogDescription, postedOn) VALUES (seq_blog.nextval, ?, ?, ?)";
	final String SELECT_ALL_BLOGS = "Select * from BLOG";
	private static final String UPDATE_BLOG = "update blog set blogTitle = ?, blogDescription = ?, postedOn = ? where blogId = ?";
	private static final String DELETE_BLOG_BY_ID = "delete from blog where blogId = ?";
	
	
	// Create method - used for inserting into the database part
	public void insertBlog(Blog blog) throws SQLException, Exception {
		PreparedStatement ps=ConnectionManager.getConnection().prepareStatement(INSERT_BLOG_QUERY);
		ps.setString(1, blog.getBlogTitle());
		ps.setString(2, blog.getBlogDescription());
		ps.setDate(3,java.sql.Date.valueOf(blog.getPostedOn()));
		ps.executeUpdate();
		}

	// Select method - to get all the blogs from the database
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

	// Update method - to update blog in the database table
	public void updateBlog(Blog blog) throws Exception {
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(UPDATE_BLOG);
		ps.setString(1, blog.getBlogTitle());
		ps.setString(2,blog.getBlogDescription());
		ps.setDate(3, java.sql.Date.valueOf(blog.getPostedOn()));
		ps.setInt(4, blog.getBlogId());
		boolean rowUpdated = ps.executeUpdate() > 0;
		if(rowUpdated)
		{
			System.out.println("Updated Blog");
		}
		else
		{
			System.out.println("Please check");
		}
	}
	
	// Delete method - to delete blog in the database table
	public void deleteBlog(int blogId) throws Exception {
		
		boolean rowDeleted;
		Connection connection = ConnectionManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_BLOG_BY_ID);
			statement.setInt(1, blogId);
			rowDeleted = statement.executeUpdate() > 0;
		
		if(rowDeleted) {
			System.out.println("Blog is deleted");
	}
		else {
			System.out.println("No blog with the given id");
		}
	}
}

package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.BlogDAO;
import model.Blog;
import utility.ConnectionManager;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// Check if connection is working or not
		
//		Connection con=null;
//		
//		con = ConnectionManager.getConnection();
//		if(con!=null)
//			System.out.println("Connection Established");
//		else 
//			System.out.println("Check connection");
		
		System.out.println("1. Create");
		System.out.println("2. Read");
		System.out.println("3. Update");
		System.out.println("4. Delete");
		Blog blog = new Blog();
		BlogDAO blogdao =  new BlogDAO();
		List<Blog> blogList = new ArrayList<Blog>();
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int choice = Integer.parseInt(br.readLine());
		
		switch (choice) {
		case 1:
			System.out.println("Create");
			String blogTitle = br.readLine();
			String blogDescription = br.readLine();
			LocalDate postedOn = LocalDate.now();
			blog.setBlogTitle(blogTitle);
			blog.setBlogDescription(blogDescription);
			blog.setPostedOn(postedOn);
			blogdao.insertBlog(blog);
			
			
			break;
		case 2:
			System.out.println("Read");
			blogList = blogdao.getAllBlogs();
			if(blogList!=null)
			{
			for(Blog blogs:blogList) {
				System.out.println(blogs.getBlogId());
				System.out.println(blogs.getBlogTitle());
				System.out.println(blogs.getBlogDescription());
				System.out.println(blogs.getPostedOn());
			}
			}
			else {
				System.out.println(blogList);
			}
			break;
		case 3:
			System.out.println("Update");
			int id = Integer.parseInt(br.readLine());
			String blog_title = br.readLine();
			String blog_description = br.readLine();
			LocalDate posted_On = LocalDate.now();
			
			blog.setBlogId(id);
			blog.setBlogTitle(blog_title);
			blog.setBlogDescription(blog_description);
			blog.setPostedOn(posted_On);
			blogdao.updateBlog(blog);
			break;
		case 4:
			System.out.println("Delete");
			int blogid = Integer.parseInt(br.readLine());
			blogdao.deleteBlog(blogid);
			break;
			
		
		
		
		
		
		
		
		
		}
		

	}

}

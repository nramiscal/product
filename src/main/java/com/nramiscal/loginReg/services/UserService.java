package com.nramiscal.loginReg.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nramiscal.loginReg.models.Role;
import com.nramiscal.loginReg.models.User;
import com.nramiscal.loginReg.repositories.RoleRepo;
import com.nramiscal.loginReg.repositories.UserRepo;


@Service
public class UserService {

	private UserRepo userRepo;
	private RoleRepo roleRepo;
	private BCryptPasswordEncoder bCPE;
	
	
	public UserService(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder bCPE) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.bCPE = bCPE;
	}
	
	// add user role
    public void saveWithUserRole(User user) {
        user.setPassword(bCPE.encode(user.getPassword()));
        user.setRoles(roleRepo.findByName("ROLE_USER"));
        userRepo.save(user);
    }
    
    public void updateWithUserRole(User user) {
        user.setRoles(roleRepo.findByName("ROLE_USER"));
        userRepo.save(user);
    }
     
    // add admin role
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCPE.encode(user.getPassword()));
        user.setRoles(roleRepo.findByName("ROLE_ADMIN"));
        userRepo.save(user);
    }    
    
    public void updateUserWithAdminRole(User user) {
        user.setRoles(roleRepo.findByName("ROLE_ADMIN"));
        userRepo.save(user);
    }
    
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
        
    }
    
    public void updateUserDate(Long id, User user) {
		user.setUpdated_at(new Date());
		userRepo.save(user);	
    }
    
    public List<User> all(){
    		return (List<User>) userRepo.findAll();
    }
    
    public void deleteUserById(Long id) {
    		userRepo.delete(id);
    }
    
    public User findUserById(Long id) {
    		return userRepo.findOne(id);
    }
    
    // initialize Roles
    // will call method when server starts so it automatically populates it for us
    public void initRoles() {
    		if (roleRepo.findAll().size() < 1) {
    			ArrayList<Role> roles = new ArrayList<Role>();
    			roles.add(new Role("ROLE_USER"));
    			roles.add(new Role("ROLE_ADMIN"));
//    			roles.add(new Role("ROLE_SUPER"));
    			roleRepo.save(roles);
    		}
    }

    // get users with subscriptions
	public List<User> getCurrentSubscribers() {
		List<User> subscribers = (List<User>) userRepo.findAll();
		Iterator<User> i = subscribers.iterator();
		while (i.hasNext()) {
		   User u = i.next();
		  if (u.getPackages().size() == 0) {
		    i.remove();
		  }
		}
		
		return subscribers;
	}
	
	public String getNextMonth() {
	   	Calendar cal = GregorianCalendar.getInstance();
	    	SimpleDateFormat df = new SimpleDateFormat("MMMM");
	
	    	Date currentMonth = new Date();
	    	cal.setTime(currentMonth);
	
	    	// current month
//	    	String currentMonthAsString = df.format(cal.getTime());
	
	    	// Add next month
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
//	    	cal.add(Calendar.MONTH, 1);
	   	String nextMonthAsString = df.format(cal.getTime());
	   	
	   	return nextMonthAsString;
	}
	
	public String getCurrentYear() {
	   	Calendar cal = GregorianCalendar.getInstance();

	    	SimpleDateFormat year = new SimpleDateFormat("yyyy");
	
	    	Date currentMonth = new Date();
	    	cal.setTime(currentMonth);
	
	    	String currentYear = year.format(cal.getTime());
	   	return currentYear;
	}
	
	public void updateUser(User user) {
		System.out.println(user);
		System.out.println(user.getPackages());
		userRepo.save(user);		
	}





       
}

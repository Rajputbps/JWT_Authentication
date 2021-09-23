package com.jwtAuth.servicedaoImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwtAuth.dao.UserDaoRepository;
import com.jwtAuth.model.UserPrinciple;
import com.jwtAuth.model.Users;
import com.jwtAuth.servicedao.UserServiceDao;

@Service
@Transactional
public class UserServiceDaoImpl implements UserServiceDao,UserDetailsService {

	
	@Autowired
	private UserDaoRepository userDaoRepository;
	
	public UserServiceDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Users addUser(Users user) {
		// TODO Auto-generated method stub
		return userDaoRepository.save(user);
	}

	@Override
	public Users updateUser(Users user) {
		// TODO Auto-generated method stub
		return userDaoRepository.saveAndFlush(user);
	}

	@Override
	public List<Users> getUsersList() {
		// TODO Auto-generated method stub
		return userDaoRepository.findAll();
	}

	@Override
	public Optional<Users> getUser(Long userId) {
		// TODO Auto-generated method stub
		return userDaoRepository.findById(userId);
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		userDaoRepository.deleteById(userId);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user = userDaoRepository.findByEmail(username)
            	.orElseThrow(() -> 
                    new UsernameNotFoundException("User Not Found with -> username or email : " + username)
    );

    return UserPrinciple.build(user);
	}
 

	@Override
	public Optional<Users> findByEmail(String email) {
		// TODO Auto-generated method stub
		return userDaoRepository.findByEmail(email);
	}

}

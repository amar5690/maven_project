package springHibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springHibernate.dao.IStudentDao;
import springHibernate.entity.Student;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	IStudentDao studentDao;
	
	@Override
	@Transactional
	public List<Student> getStudent() {
		return studentDao.getStudent();
	}



}

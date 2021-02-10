package springHibernate.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springHibernate.entity.Student;

@Repository
public class StudentDaoImpl implements IStudentDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Student> getStudent() {

		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> cquery = cb.createQuery(Student.class);
		Root<Student> root = cquery.from(Student.class);
		cquery.select(root);
		Query query = session.createQuery(cquery);
		return query.getResultList();

	}

}

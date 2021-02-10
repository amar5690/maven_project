"# Spring5 with hibernate 5 "

Qustion : diff between SessionFactory.OpenSession and SessionFactory.getCurrentSession();
answer : 
openSession() will return a new session object on every call, which is actually a instance of org.hibernate.impl.SessionImpl.
We can use this method when we decided to manage the Session our self.
It does not try to store or pull the session from the current context.
If we use this method, we need to flush() and close() the session. It does not flush and close() automatically.

getCurrentSession():

Session getCurrentSession() throws HibernateException

Obtains the current session. The "current session" refers to a Hibernate Session bound by Hibernate behind the scenes,
 to the transaction scope.A session is opened whenever getCurrentSession() 
 is called for the first time and closed when the transaction ends. This creates a brand new session if one does not exist
 or uses an existing one if one already exists. It automatically configured with both auto-flush and auto-close attributes as true means 
 Session will be automatically flushed and closed.
 
 

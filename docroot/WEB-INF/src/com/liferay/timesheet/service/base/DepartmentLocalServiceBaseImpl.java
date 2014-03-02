/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.timesheet.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.timesheet.model.Department;
import com.liferay.timesheet.service.DepartmentLocalService;
import com.liferay.timesheet.service.persistence.DepartmentPersistence;
import com.liferay.timesheet.service.persistence.ProjectPersistence;
import com.liferay.timesheet.service.persistence.TaskPersistence;
import com.liferay.timesheet.service.persistence.TaskSessionFinder;
import com.liferay.timesheet.service.persistence.TaskSessionPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the department local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.timesheet.service.impl.DepartmentLocalServiceImpl}.
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see com.liferay.timesheet.service.impl.DepartmentLocalServiceImpl
 * @see com.liferay.timesheet.service.DepartmentLocalServiceUtil
 * @generated
 */
public abstract class DepartmentLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements DepartmentLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.timesheet.service.DepartmentLocalServiceUtil} to access the department local service.
	 */

	/**
	 * Adds the department to the database. Also notifies the appropriate model listeners.
	 *
	 * @param department the department
	 * @return the department that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Department addDepartment(Department department)
		throws SystemException {
		department.setNew(true);

		return departmentPersistence.update(department);
	}

	/**
	 * Creates a new department with the primary key. Does not add the department to the database.
	 *
	 * @param departmentId the primary key for the new department
	 * @return the new department
	 */
	@Override
	public Department createDepartment(long departmentId) {
		return departmentPersistence.create(departmentId);
	}

	/**
	 * Deletes the department with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param departmentId the primary key of the department
	 * @return the department that was removed
	 * @throws PortalException if a department with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Department deleteDepartment(long departmentId)
		throws PortalException, SystemException {
		return departmentPersistence.remove(departmentId);
	}

	/**
	 * Deletes the department from the database. Also notifies the appropriate model listeners.
	 *
	 * @param department the department
	 * @return the department that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Department deleteDepartment(Department department)
		throws SystemException {
		return departmentPersistence.remove(department);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Department.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return departmentPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.DepartmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return departmentPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.DepartmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return departmentPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return departmentPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return departmentPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public Department fetchDepartment(long departmentId)
		throws SystemException {
		return departmentPersistence.fetchByPrimaryKey(departmentId);
	}

	/**
	 * Returns the department with the primary key.
	 *
	 * @param departmentId the primary key of the department
	 * @return the department
	 * @throws PortalException if a department with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 * @throws com.liferay.timesheet.NoSuchDepartmentException
	 */
	@Override
	public Department getDepartment(long departmentId)
		throws PortalException, SystemException,
			com.liferay.timesheet.NoSuchDepartmentException {
		return departmentPersistence.findByPrimaryKey(departmentId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return departmentPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the departments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.DepartmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of departments
	 * @param end the upper bound of the range of departments (not inclusive)
	 * @return the range of departments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Department> getDepartments(int start, int end)
		throws SystemException {
		return departmentPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of departments.
	 *
	 * @return the number of departments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getDepartmentsCount() throws SystemException {
		return departmentPersistence.countAll();
	}

	/**
	 * Updates the department in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param department the department
	 * @return the department that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Department updateDepartment(Department department)
		throws SystemException {
		return departmentPersistence.update(department);
	}

	/**
	 * Returns the department local service.
	 *
	 * @return the department local service
	 */
	public com.liferay.timesheet.service.DepartmentLocalService getDepartmentLocalService() {
		return departmentLocalService;
	}

	/**
	 * Sets the department local service.
	 *
	 * @param departmentLocalService the department local service
	 */
	public void setDepartmentLocalService(
		com.liferay.timesheet.service.DepartmentLocalService departmentLocalService) {
		this.departmentLocalService = departmentLocalService;
	}

	/**
	 * Returns the department persistence.
	 *
	 * @return the department persistence
	 */
	public DepartmentPersistence getDepartmentPersistence() {
		return departmentPersistence;
	}

	/**
	 * Sets the department persistence.
	 *
	 * @param departmentPersistence the department persistence
	 */
	public void setDepartmentPersistence(
		DepartmentPersistence departmentPersistence) {
		this.departmentPersistence = departmentPersistence;
	}

	/**
	 * Returns the project local service.
	 *
	 * @return the project local service
	 */
	public com.liferay.timesheet.service.ProjectLocalService getProjectLocalService() {
		return projectLocalService;
	}

	/**
	 * Sets the project local service.
	 *
	 * @param projectLocalService the project local service
	 */
	public void setProjectLocalService(
		com.liferay.timesheet.service.ProjectLocalService projectLocalService) {
		this.projectLocalService = projectLocalService;
	}

	/**
	 * Returns the project persistence.
	 *
	 * @return the project persistence
	 */
	public ProjectPersistence getProjectPersistence() {
		return projectPersistence;
	}

	/**
	 * Sets the project persistence.
	 *
	 * @param projectPersistence the project persistence
	 */
	public void setProjectPersistence(ProjectPersistence projectPersistence) {
		this.projectPersistence = projectPersistence;
	}

	/**
	 * Returns the task local service.
	 *
	 * @return the task local service
	 */
	public com.liferay.timesheet.service.TaskLocalService getTaskLocalService() {
		return taskLocalService;
	}

	/**
	 * Sets the task local service.
	 *
	 * @param taskLocalService the task local service
	 */
	public void setTaskLocalService(
		com.liferay.timesheet.service.TaskLocalService taskLocalService) {
		this.taskLocalService = taskLocalService;
	}

	/**
	 * Returns the task persistence.
	 *
	 * @return the task persistence
	 */
	public TaskPersistence getTaskPersistence() {
		return taskPersistence;
	}

	/**
	 * Sets the task persistence.
	 *
	 * @param taskPersistence the task persistence
	 */
	public void setTaskPersistence(TaskPersistence taskPersistence) {
		this.taskPersistence = taskPersistence;
	}

	/**
	 * Returns the task session local service.
	 *
	 * @return the task session local service
	 */
	public com.liferay.timesheet.service.TaskSessionLocalService getTaskSessionLocalService() {
		return taskSessionLocalService;
	}

	/**
	 * Sets the task session local service.
	 *
	 * @param taskSessionLocalService the task session local service
	 */
	public void setTaskSessionLocalService(
		com.liferay.timesheet.service.TaskSessionLocalService taskSessionLocalService) {
		this.taskSessionLocalService = taskSessionLocalService;
	}

	/**
	 * Returns the task session persistence.
	 *
	 * @return the task session persistence
	 */
	public TaskSessionPersistence getTaskSessionPersistence() {
		return taskSessionPersistence;
	}

	/**
	 * Sets the task session persistence.
	 *
	 * @param taskSessionPersistence the task session persistence
	 */
	public void setTaskSessionPersistence(
		TaskSessionPersistence taskSessionPersistence) {
		this.taskSessionPersistence = taskSessionPersistence;
	}

	/**
	 * Returns the task session finder.
	 *
	 * @return the task session finder
	 */
	public TaskSessionFinder getTaskSessionFinder() {
		return taskSessionFinder;
	}

	/**
	 * Sets the task session finder.
	 *
	 * @param taskSessionFinder the task session finder
	 */
	public void setTaskSessionFinder(TaskSessionFinder taskSessionFinder) {
		this.taskSessionFinder = taskSessionFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.timesheet.model.Department",
			departmentLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.timesheet.model.Department");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return Department.class;
	}

	protected String getModelClassName() {
		return Department.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = departmentPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.timesheet.service.DepartmentLocalService.class)
	protected com.liferay.timesheet.service.DepartmentLocalService departmentLocalService;
	@BeanReference(type = DepartmentPersistence.class)
	protected DepartmentPersistence departmentPersistence;
	@BeanReference(type = com.liferay.timesheet.service.ProjectLocalService.class)
	protected com.liferay.timesheet.service.ProjectLocalService projectLocalService;
	@BeanReference(type = ProjectPersistence.class)
	protected ProjectPersistence projectPersistence;
	@BeanReference(type = com.liferay.timesheet.service.TaskLocalService.class)
	protected com.liferay.timesheet.service.TaskLocalService taskLocalService;
	@BeanReference(type = TaskPersistence.class)
	protected TaskPersistence taskPersistence;
	@BeanReference(type = com.liferay.timesheet.service.TaskSessionLocalService.class)
	protected com.liferay.timesheet.service.TaskSessionLocalService taskSessionLocalService;
	@BeanReference(type = TaskSessionPersistence.class)
	protected TaskSessionPersistence taskSessionPersistence;
	@BeanReference(type = TaskSessionFinder.class)
	protected TaskSessionFinder taskSessionFinder;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private DepartmentLocalServiceClpInvoker _clpInvoker = new DepartmentLocalServiceClpInvoker();
}
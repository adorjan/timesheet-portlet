package com.liferay.timesheet.bean.view;

import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.primefaces.ProjectTreeNode;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.TreeNode;

public abstract class AbstractViewBean implements Serializable {

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelectedProjectNode() {
		return selectedProjectNode;
	}

	public void setSelectedProjectNode(TreeNode selectedProjectNode) {
		this.selectedProjectNode = selectedProjectNode;

		if (selectedProjectNode != null) {
			selectedProject =
				((ProjectTreeNode)selectedProjectNode).getProject();
		}
	}

	public TaskSession getCurrentTaskSession() {
		return currentTaskSession;
	}

	public void setCurrentTaskSession(TaskSession currentTaskSession) {
		this.currentTaskSession = currentTaskSession;
	}

	public Date getTodayWithoutTime() {
		return todayWithoutTime;
	}

	public void setTodayWithoutTime(Date todayWithoutTime) {
		this.todayWithoutTime = todayWithoutTime;
	}

	private Date todayWithoutTime = null;
	private TaskSession currentTaskSession = null;
	private TreeNode root = null;
	private Project selectedProject = null;
	private TreeNode selectedProjectNode = null;

	private static final long serialVersionUID = 1L;
}
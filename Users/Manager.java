package Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Enums.Status;
import Enums.UrgencyLevel;
import Patterns.SortingContext;
import Research.Researcher;
import SystemParts.Comment;
import SystemParts.Message;
import SystemParts.News;
import SystemParts.Report;
import SystemParts.Request;
import SystemParts.School;
import SystemParts.Course;

public class Manager extends Employee {
	private List<Student> students = new ArrayList<>();
	private List<Teacher> teachers = new ArrayList<>();
	private List<Course> courses = new ArrayList<>();
	private List<News> news = new ArrayList<>();
	private List<Request> requests;
	private List<Message> messages;
	private SortingContext<Student> studentSortingContext = new SortingContext<>();
    private SortingContext<Teacher> teacherSortingContext = new SortingContext<>();

    public Manager(String name, String surname, String email) {
        super(name, surname, email);
    	this.id = generateUniqueID();
    }

	public void approveStudentRegistration(Student student, Course course) {
		if (!courses.contains(course)) {
			System.out.println("Course " + course.getName() + " is not added to the system yet.");
			return;
		}

		School studentSchool = student.getSchool();
		if (course.getType() == CourseType.MAJOR && !studentSchool.getMajorCourses().contains(course)) {
			System.out.println("Course " + course.getName() + " is not a valid major course for the student's school.");
			return;
		}

		if (course.getType() == CourseType.MINOR && !studentSchool.getMinorCourses().contains(course)) {
			System.out.println("Course " + course.getName() + " is not a valid minor course for the student's school.");
			return;
		}

		if (course.getType() == CourseType.ELECTIVE) {
			System.out.println("Elective course " + course.getName() + " approved for " + student.getName() + ".");
			student.addCourse(course);
			return;
		}

		System.out.println("Registration approved: " + student.getName() + " for course " + course.getName());
		student.addCourse(course);
	}

	public void addCourse(School school, Course course, CourseType type) {
		if (school.getMajorCourses().contains(course) || school.getMinorCourses().contains(course)
				|| courses.contains(course)) {
			System.out.println("Course " + course.getName() + " is already added to the school or system.");
			return;
		}

		courses.add(course);

		switch (type) {
		case MAJOR:
			school.addMajorCourse(course);
			System.out.println("Added course as MAJOR to school " + school.getName() + ": " + course.getName());
			break;
		case MINOR:
			school.addMinorCourse(course);
			System.out.println("Added course as MINOR to school " + school.getName() + ": " + course.getName());
			break;
		case ELECTIVE:
			System.out.println("Added course as ELECTIVE " + course.getName());
			break;
		}
	}

	public void assignCourseToTeacher(Course course, Teacher teacher) {
		course.addInstructor(teacher);
		teacher.addCourse(course);
		System.out.println(teacher.getName() + " has been assigned to course: " + course.getCourseName());
	}

	public Report createAcademicReport() {
		Report report = new Report();

		report.setTotalStudents(students.size());
		report.setTotalTeachers(teachers.size());
		report.setTotalCourses(courses.size());

		Map<String, Integer> studentsPerCourse = new HashMap<>();
		for (Course course : courses) {
			int count = 0;
			for (Student student : students) {
				if (student.getGrades().containsKey(course)) {
					count++;
				}
			}
			studentsPerCourse.put(course.getCourseName(), count);
		}
		report.setStudentsPerCourse(studentsPerCourse);

		Map<String, Double> averageGradesPerCourse = new HashMap<>();
		for (Course course : courses) {
			double totalGrades = 0;
			int count = 0;

			for (Student student : students) {
				Double grade = student.getGradeForCourse(course);
				if (grade != null) {
					totalGrades += grade;
					count++;
				}
			}
			double averageGrade = (count == 0) ? 0 : totalGrades / count;
			averageGradesPerCourse.put(course.getCourseName(), averageGrade);
		}
		report.setAverageGradesPerCourse(averageGradesPerCourse);

		return report;
	}

	public void viewStudentsInfo(List<Student> students, Comparator<Student> comparator) {
	    List<Student> sortedStudents = studentSortingContext.sort(students, comparator);
	    sortedStudents.forEach(System.out::println);
	}

	public void viewTeachersInfo(List<Teacher> teachers, Comparator<Teacher> comparator) {
	    List<Teacher> sortedTeachers = teacherSortingContext.sort(teachers, comparator);
	    sortedTeachers.forEach(System.out::println);
	}

	public List<Request> viewRequests() {
		List<Request> signedRequests = new ArrayList<>();
		for (Request request : requests) {
			if (request.isSigned()) {
				signedRequests.add(request);
			}
		}
		return signedRequests;
	}

	public void addNews(News newsItem) {
		news.add(newsItem);
		System.out.println("News added: " + newsItem.getTopic());
	}

	public void removeNews(String topic) {
		for (int i = 0; i < news.size(); i++) {
			if (news.get(i).getTopic().equals(topic)) {
				news.remove(i);
				System.out.println("News removed with topic: " + topic);
				return;
			}
		}
		System.out.println("News with topic " + topic + " not found.");
	}

	public void updateNews(String topic, String newDescription) {
		for (News newsItem : news) {
			if (newsItem.getTopic().equals(topic)) {
				newsItem.setDescription(newDescription);
				System.out.println("News updated: " + topic);
				return;
			}
		}
		System.out.println("News with topic " + topic + " not found.");
	}

	public List<News> viewAllNews() {
		return new ArrayList<>(news);
	}

	public News findNewsByTopic(String topic) {
		for (News newsItem : news) {
			if (newsItem.getTopic().equals(topic)) {
				return newsItem;
			}
		}
		return null;
	}

	/*public void pinNews(String topic) {
		News newsItem = findNewsByTopic(topic);
		if (newsItem != null) {
			newsItem.setPinned(true);
			System.out.println("News pinned: " + topic);
		} else {
			System.out.println("News with topic " + topic + " not found.");
		}
	}*/

	public void sendMessage(User sender, User recipient, String content, Message.UrgencyLevel priority) {
		Message newMessage = new Message(sender, recipient, content, priority);
		messages.add(newMessage);
		System.out.println("Message sent from " + sender.getName() + " to " + recipient.getName());
	}

	public List<Message> viewMessagesByStatus(Status status) {
		List<Message> filteredMessages = new ArrayList<>();
		for (Message message : messages) {
			if (message.getStatus() == status) {
				filteredMessages.add(message);
			}
		}
		return filteredMessages;
	}

	public List<Message> filterMessagesByPriority(UrgencyLevel priority) {
		List<Message> filteredMessages = new ArrayList<>();
		for (Message message : messages) {
			if (message.filterByPriority(priority)) {
				filteredMessages.add(message);
			}
		}
		return filteredMessages;
	}

	public List<Message> viewAllMessages() {
		return new ArrayList<>(messages);
	}

	public void markAllMessagesAsRead() {
		for (Message message : messages) {
			message.markAsRead();
		}
		System.out.println("All messages have been marked as read.");
	}

	public void markMessageAsRead(Message message) {
		message.markAsRead();
		System.out.println("Message from " + message.getSender().getName() + " to " + message.getRecipient().getName()
				+ " has been marked as read.");
	}

	public void deleteMessage(Message message) {
		messages.remove(message);
		System.out.println("Message from " + message.getSender().getName() + " to " + message.getRecipient().getName()
				+ " has been deleted.");
	}

	public List<Message> viewMessagesByRecipient(User recipient) {
		List<Message> filteredMessages = new ArrayList<>();
		for (Message message : messages) {
			if (message.getRecipient().equals(recipient)) {
				filteredMessages.add(message);
			}
		}
		return filteredMessages;
	}

	public List<Message> viewMessagesBySender(User sender) {
		List<Message> filteredMessages = new ArrayList<>();
		for (Message message : messages) {
			if (message.getSender().equals(sender)) {
				filteredMessages.add(message);
			}
		}
		return filteredMessages;
	}

	public List<Message> viewMessages(Status status, UrgencyLevel priority) {
		List<Message> filteredMessages = new ArrayList<>();
		for (Message message : messages) {
			if (message.getStatus() == status && message.getPriority() == priority) {
				filteredMessages.add(message);
			}
		}
		return filteredMessages;
	}
	
	public void addCommentToNews(News newsItem, Comment comment) {
        newsItem.addComment(comment);
        System.out.println("Comment added to news: " + newsItem.getTopic());
    }

    public void removeCommentFromNews(News newsItem, Comment comment) {
        newsItem.removeComment(comment);
        System.out.println("Comment removed from news: " + newsItem.getTopic());
    }
    
    //переделать вьюшку для комментов-
    public void viewComments(News newsItem) {
        System.out.println("Comments for news: " + newsItem.getTopic());
        for (Comment comment : newsItem.getComments()) {
            System.out.println(comment.getAuthor().getName() + " (" + comment.getTimestamp() + "): " + comment.getContent());
        }
}

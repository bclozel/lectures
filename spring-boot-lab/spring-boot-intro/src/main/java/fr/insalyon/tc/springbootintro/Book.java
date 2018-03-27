package fr.insalyon.tc.springbootintro;

@SuppressWarnings("serial")
public class Book {

	private Long id;

	private String isbn;

	private String author;

	private String title;

	private String version;

	public Book() {
	}

	public Book(String isbn, String author, String title, String version) {
		this.isbn = isbn;
		this.author = author;
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Book{" +
				"isbn='" + isbn + '\'' +
				", author='" + author + '\'' +
				", title='" + title + '\'' +
				", version='" + version + '\'' +
				'}';
	}
}
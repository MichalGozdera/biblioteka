package com.sda.biblioteka.servlets.spring;

import com.sda.biblioteka.servlets.spring.db.BookContext;
import com.sda.biblioteka.servlets.spring.db.UserContext;
import com.sda.biblioteka.servlets.spring.db.model.Book;
import com.sda.biblioteka.servlets.spring.db.model.Category;
import com.sda.biblioteka.servlets.spring.db.model.Lend;
import com.sda.biblioteka.servlets.spring.db.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class LibraryController {
    UserContext uc0 = new UserContext();
    BookContext bc0 = new BookContext();

    @RequestMapping(value = "/home")
    public ModelAndView libraryHomePage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("booksCount", bc0.getBooksCount());
        modelAndView.addObject("usersCount", uc0.getUsersCount());
        modelAndView.setViewName("libraryMain.jsp");
        return modelAndView;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ModelAndView libraryBooksPage(@RequestParam(value = "title", required = false) String title,
                                         @RequestParam(value = "author", required = false) String author,
                                         @RequestParam(value = "category", required = false) String category,
                                         @RequestParam(value = "yearOfRelease", required = false) Integer yearOfRelease) {
        ModelAndView modelAndView = new ModelAndView();
        StringBuilder sb = new StringBuilder("select b from Book b where b.id>0");
        if (title != null) {
            if (!title.equalsIgnoreCase("")) {
                sb.append(" and lower(b.title) like\'%" + title.toLowerCase() + "%\'");
            }
        }
        if (author != null) {
            if (!author.equalsIgnoreCase("")) {
                sb.append(" and lower(b.author) like\'%" + author.toLowerCase() + "%\'");
            }
        }
        if (category != null) {
            if (!category.equalsIgnoreCase("")) {
                sb.append(" and lower(b.category)=\'" + category.toLowerCase() + "\'");
            }

        }
        if (yearOfRelease != null) {
            sb.append(" and b.yearOfRelease=" + yearOfRelease);
        }
        modelAndView.addObject("books", bc0.findBook(sb.toString()));
        modelAndView.setViewName("libraryBooks.jsp");
        return modelAndView;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String findBook(@RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "author", required = false) String author,
                           @RequestParam(value = "category", required = false) String category,
                           @RequestParam(value = "yearOfRelease", required = false) Integer yearOfRelease) {
        StringBuilder sb = new StringBuilder("redirect:/books?username=log");
        if (title != null) {
            if (!title.equalsIgnoreCase("")) {
                sb.append("&title=" + title);
            }
        }
        if (author != null) {
            if (!author.equalsIgnoreCase("")) {
                sb.append("&author=" + author);
            }
        }
        if (category != null) {
            if (!category.equalsIgnoreCase("")) {
                sb.append("&category=" + category);
            }

        }
        if (yearOfRelease != null) {
            sb.append("&yearOfRelease=" + yearOfRelease);
        }
        return sb.toString();
    }

    @RequestMapping(value = "/myBooks", method = RequestMethod.GET)
    public ModelAndView libraryMyBooksPage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user= (User) session.getAttribute("user");
        Integer userId=user.getUserId();
        modelAndView.addObject("books", bc0.findBook("select b from Lend l inner join l.book b where l.returnDate IS NULL and l.user=" + userId));
        modelAndView.setViewName("libraryMyBooks.jsp");
        return modelAndView;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView libraryConfirmPage(@RequestParam(value = "id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("book", bc0.findBook("select b from Book b where b.id=" + id).get(0));

        modelAndView.setViewName("libraryReservation.jsp");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView libraryLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("libraryLogin.jsp");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "login") String login,
                        @RequestParam(value = "password") String password,
                        HttpServletRequest request) {
        User user0 = uc0.findUser(login);
        if (user0 != null) {
            if (user0.getPassword().equals(password)) {
                user0.setInvPassCount(0);
                uc0.updateUser(user0);
                HttpSession session = request.getSession();
                session.setAttribute("user", user0);
                return "redirect:/home";
            } else {
                if (user0.getInvPassCount() < 3) {
                    int temp = user0.getInvPassCount();
                    temp++;
                    user0.setInvPassCount(temp);
                    uc0.updateUser(user0);
                    return "redirect:/login?login=invP";
                } else {
                    return "redirect:https://www.youtube.com/watch?v=LT3kI3BsxlQ";
                }

            }
        } else {
            return "redirect:/login?login=invL";
        }

    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView libraryRegisterPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("libraryRegister.jsp");
        return modelAndView;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute(name = "registerForm") User user) {
        if (!uc0.addUser(user)) {
            return "redirect:/register?login=" + user.getLogin();
        } else {
            return "redirect:/login?login=" + user.getLogin();
        }
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public ModelAndView libraryAddBookPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("libraryAddBook.jsp");
        return modelAndView;
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public String addBook(@RequestParam(value = "title") String title,
                          @RequestParam(value = "author") String author,
                          @RequestParam(value = "category") String category,
                          @RequestParam(value = "yearOfRelease") Integer yearOfRelease) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearOfRelease(yearOfRelease);
        switch (category) {
            case "Liryka":
                book.setCategory(Category.LIRYKA);
                break;
            case "Epika":
                book.setCategory(Category.EPIKA);
                break;
            case "Dramat":
                book.setCategory(Category.DRAMAT);
                break;
        }
        bc0.addBook(book);
        return "redirect:/home";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/home";
    }


    @RequestMapping(value = "/return", method = RequestMethod.POST)
    public String returnBook(@RequestParam(value = "id") Integer id) {
        bc0.returnBook(id);
        return "redirect:/books";
    }

    @RequestMapping(value = "/borrow", method = RequestMethod.POST)
    public String borrow(@RequestParam(value = "id") Integer id, HttpSession session) {
        Book book = bc0.findBook("select b from Book b where b.id=" + id).get(0);
        User user = (User)session.getAttribute("user");
        Lend lend = new Lend();
        lend.setBook(book);
        lend.setUser(user);
        lend.setLendingDate(new Date());
        bc0.borrowBook(lend);
        return "redirect:/home";
    }
}

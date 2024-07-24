package kz.spring.auth.auth.service;

import kz.spring.auth.auth.exception.CustomerException;
import kz.spring.auth.auth.model.Customer;
import kz.spring.auth.auth.model.Role;
import kz.spring.auth.auth.security.CustomerDetails;
import kz.spring.auth.auth.storage.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public Customer create(Customer user) {
        if (customerRepository.existsByUsername(user.getUsername())) {
            throw new CustomerException(HttpStatus.CONFLICT, "Пользователь с таким именем уже существует");
        }

        return save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public UserDetails getByUsername(String username) {
        Customer customer = customerRepository.findCustomerByUsername(username)
                .orElseThrow(() -> new CustomerException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
        return new CustomerDetails(customer);
    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public UserDetails getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    /**
     * Выдача прав администратора текущему пользователю
     * <p>
     * Нужен для демонстрации
     */
    @Deprecated
    public void getAdmin() {
        CustomerDetails user = (CustomerDetails) getCurrentUser();
        user.customer().setRole(Role.ROLE_ADMIN);
        save(user.customer());
    }
}

package com.linguo.users;

import com.linguo.users.model.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {
}

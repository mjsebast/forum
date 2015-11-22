package com.linguo.users;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {
}

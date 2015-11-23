package com.linguo.thread.repository;


import com.linguo.thread.model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ThreadRepository extends PagingAndSortingRepository<Thread, Long> {
}

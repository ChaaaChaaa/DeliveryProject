package com.programmers.repository.menu;

import com.programmers.domain.Menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    Optional<Menu> findById(@Nullable Long menuId);

    Menu save(@Nullable Menu menu);

    void delete(@Nullable Menu menu);
}
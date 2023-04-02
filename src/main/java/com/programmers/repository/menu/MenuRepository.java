package com.programmers.repository.menu;

import com.programmers.domain.Menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;


import java.util.Optional;

import javax.validation.constraints.NotNull;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
   @NotNull
    Optional<Menu> findById(@Nullable Long menuId);

    Optional<Menu> findByMenu(Long menuId);

    Store findStoreByMenuId(Long menuId);

    Menu save(@Nullable Menu menu);

    void delete(@Nullable Menu menu);
}

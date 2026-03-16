package com.ubisam.exam1.api.addresses;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubisam.exam1.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}

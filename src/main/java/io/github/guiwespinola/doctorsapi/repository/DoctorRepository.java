package io.github.guiwespinola.doctorsapi.repository;

import io.github.guiwespinola.doctorsapi.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}

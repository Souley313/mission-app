package dev.mission.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import dev.mission.entite.Mission;

@DataJpaTest
class MissionRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	MissionRepository missionRepository;

	@Test
	void findByDateDebutGreaterThanEqual() {
		Mission m = new Mission();
		m.setLibelle("TestMission");
		m.setTauxJournalier(new BigDecimal("100.0"));
		m.setDateDebut(LocalDate.of(2020, 8, 17));
		m.setDateFin(LocalDate.of(2020, 11, 4));
		entityManager.persist(m);
		List<Mission> missions = missionRepository.ListerProchainesMissions(LocalDate.of(2020, 8, 17));
		assertThat(missions).size().isEqualTo(1);
	}
	
	@Test
	void findByDateDebutGreaterThanEqualAndTauxJournalierGreaterThanEqual() {
		Mission m2 = new Mission();
		m2.setLibelle("TestMission2");
		m2.setTauxJournalier(new BigDecimal("103.00"));
		m2.setDateDebut(LocalDate.of(2019, 1, 1));
		m2.setDateFin(LocalDate.of(2019, 3, 4));
		entityManager.persist(m2);
		List<Mission> missions = missionRepository.ListerProchainesMissionsParTJM(LocalDate.of(2019, 1, 1), new BigDecimal("103.00"));
		assertThat(missions).size().isEqualTo(1);
	}
}

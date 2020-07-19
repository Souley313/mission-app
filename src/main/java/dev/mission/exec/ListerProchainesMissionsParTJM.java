package dev.mission.exec;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import dev.mission.entite.Mission;
import dev.mission.repository.MissionRepository;

@Controller
@Profile("listerTJM")
public class ListerProchainesMissionsParTJM implements Runnable{
	
	private MissionRepository missionRepository;
	
	/**
	 * @param missionRepository
	 * @return 
	 */
	public ListerProchainesMissionsParTJM(MissionRepository missionRepository) {
		super();
		this.missionRepository = missionRepository;
	}

	@Override
	public void run() {
		List<Mission> missions = missionRepository.ListerProchainesMissionsParTJM(LocalDate.of(2019, 1, 1), new BigDecimal("100.00"));;
		for (Mission mission : missions) {
			System.out.println(mission.getLibelle() + " : " + mission.getDateDebut() + "-" + mission.getDateFin()
					+ " (taux : " + mission.getTauxJournalier() + ")");
		}
		
	}

}

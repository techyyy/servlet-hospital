package com.hospital.Hospital.web.command;

import com.hospital.Hospital.web.command.impl.*;
import com.hospital.Hospital.web.command.impl.admin.*;
import com.hospital.Hospital.web.command.impl.common.*;
import com.hospital.Hospital.web.command.impl.doctor.DischargePatient;
import com.hospital.Hospital.web.command.impl.doctor.SortMyPatientsByAlphabet;
import com.hospital.Hospital.web.command.impl.doctor.SortMyPatientsByBirthDate;
import com.hospital.Hospital.web.command.impl.doctor.ViewPatientsByDoctor;
import com.hospital.Hospital.web.command.impl.nurse.Appointment;
import com.hospital.Hospital.web.command.impl.outofcontrol.Login;
import com.hospital.Hospital.web.command.impl.outofcontrol.Logout;
import com.hospital.Hospital.web.command.impl.outofcontrol.SetLanguage;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put(null, new NullCase());
        commands.put("login", new Login());
        commands.put("main", new MainPage());
        commands.put("viewPatientsByDoctor", new ViewPatientsByDoctor());
        commands.put("viewAllPatients", new ViewAllPatients());
        commands.put("viewAllDoctors", new ViewAllDoctors());
        commands.put("appointment", new Appointment());
        commands.put("editPatient", new EditPatient());
        commands.put("editPatientApply", new EditPatientApply());
        commands.put("setTreatmentForPatient", new SetTreatmentForPatient());
        commands.put("setTreatmentForPatientApply", new SetTreatmentForPatientApply());
        commands.put("dischargePatient", new DischargePatient());
        commands.put("adminPanel", new AdminPanel());
        commands.put("registerADoctor", new RegisterADoctor());
        commands.put("registerAPatient", new RegisterAPatient());
        commands.put("registerAPatientSubmit", new RegisterAPatientSubmit());
        commands.put("setDoctorForAPatient", new SetDoctorForAPatient());
        commands.put("registerADoctorSubmit", new RegisterADoctorSubmit());
        commands.put("registerANurse", new RegisterANurse());
        commands.put("registerANurseSubmit", new RegisterANurseSubmit());
        commands.put("setDoctorForAPatientSubmit", new SetDoctorForAPatientSubmit());
        commands.put("logout", new Logout());
        commands.put("viewHospitalCard", new ViewHospitalCard());
        commands.put("setLanguage", new SetLanguage());
        commands.put("sortDoctorsByAlphabet", new SortDoctorsByAlphabet());
        commands.put("sortDoctorsByPosition", new SortDoctorsByPosition());
        commands.put("sortMyPatientsByAlphabet", new SortMyPatientsByAlphabet());
        commands.put("sortMyPatientsByBirthDate", new SortMyPatientsByBirthDate());
        commands.put("sortAllPatientsByAlphabet", new SortAllPatientsByAlphabet());
        commands.put("sortAllPatientsByBirthDate", new SortAllPatientsByBirthDate());
    }

    public static Command get(String name) {
        return commands.get(name);
    }

}

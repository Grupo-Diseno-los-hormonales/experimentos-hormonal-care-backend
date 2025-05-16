Feature: Selección de Rol
  Como usuario de "HormonalCare"
  Quiero elegir el rol de paciente o médico
  Para usar la app según mis necesidades

  Scenario Outline: Selección de rol
    Given el usuario ha iniciado sesión
    When selecciona el rol "<rol>"
    Then el sistema carga la interfaz para "<rol>"

    Examples:
      | rol     |
      | ROL_DOCTOR|
      | ROL_PATIENT  |

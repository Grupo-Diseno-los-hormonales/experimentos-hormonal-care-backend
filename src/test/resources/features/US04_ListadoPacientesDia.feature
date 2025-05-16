Feature: Visualización de lista de pacientes programados para el día como médico
  Como médico
  Quiero ver la lista de pacientes programados para el día
  Para organizar mis citas médicas

  Scenario Outline: Listado diario de pacientes
    Given el médico está autenticado
    When accede a "Citas del día"
    Then el sistema muestra los pacientes agendados con hora y motivo

    Examples:
      | paciente          | hora    | motivo             |
      | Ana López         | 09:00am | Control Hormonal   |
      | José Martínez     | 11:00am | Revisión de análisis|

  Scenario: Sin citas para el día
    Given el médico está autenticado
    When accede a "Citas del día"
    Then el sistema muestra "No hay citas programadas para hoy"

Feature: Visualización y Gestión de Horario por parte del Médico
  Como médico
  Quiero ver y crear eventos en mi agenda
  Para organizar mis actividades diarias

  Scenario: Visualizar horario existente
    Given el médico está autenticado
    When accede a "Mi agenda"
    Then el sistema muestra los eventos programados

  Scenario Outline: Crear nuevo evento
    Given el médico está en la sección "Mi agenda"
    When añade un nuevo evento con título "<titulo>", fecha "<fecha>" y hora "<hora>"
    Then el sistema guarda el evento y lo muestra en el calendario

    Examples:
      | titulo                  | fecha      | hora    |
      | Consulta con paciente   | 2025-05-20 | 10:00am |
      | Revisión de exámenes    | 2025-05-21 | 03:00pm |

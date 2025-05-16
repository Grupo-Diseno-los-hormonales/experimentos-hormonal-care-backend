Feature: Visualización detallada de información de pacientes como médico
  Como médico
  Quiero acceder a una vista general del estado de mis pacientes
  Para organizar mis prioridades clínicas

  Scenario Outline: Visualizar lista de pacientes con estado
    Given el médico ha iniciado sesión
    When accede a la sección "Pacientes"
    Then el sistema muestra una lista con nombre "<nombre>", estado clínico "<estado>" y próxima cita "<proxima_cita>"

    Examples:
      | nombre         | estado      | proxima_cita        |
      | Carlos Perez   | Estable     | 2025-04-26 18:00    |
      | Ana Garcia     | Crítico     | 2025-04-25 14:00    |
      | Sofia Lopez    | Recuperado  | 2025-04-27 09:00    |

  Scenario: Sin pacientes registrados
    Given el médico ha iniciado sesión
    When accede a la sección "Pacientes"
    Then el sistema muestra el mensaje "No hay pacientes asignados"
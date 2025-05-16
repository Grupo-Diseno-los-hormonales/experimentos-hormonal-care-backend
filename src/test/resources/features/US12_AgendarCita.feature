
Feature: Agendar cita con endocrinólogo
  Como paciente
  Quiero agendar cita con médico endocrinólogo
  Para recibir tratamiento hormonal remoto

  Scenario Outline: Selección y agendamiento de cita
    Given el paciente está autenticado
    When selecciona médico "<medico>", fecha "<fecha>" y hora "<hora>" disponibles
    And confirma la cita
    Then el sistema guarda la cita y notifica al paciente

    Examples:
      | medico          | fecha      | hora    |
      | Dra. Martínez   | 2025-05-22 | 09:00am |
      | Dr. Rodríguez   | 2025-05-23 | 02:00pm |

  Scenario: Intento de agendar sin disponibilidad
    Given el paciente está autenticado
    When selecciona fecha u hora sin disponibilidad
    Then el sistema muestra "Horario no disponible"

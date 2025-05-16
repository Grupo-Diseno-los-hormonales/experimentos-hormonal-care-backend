Feature: Ver próximas citas
  Como paciente
  Quiero ver mis próximas consultas y recordatorios
  Para mantenerme organizado

  Scenario Outline: Mostrar próximas citas y recordatorios
    Given el paciente está autenticado
    When accede a "Próximas citas"
    Then el sistema muestra la cita "<tipo>" con el detalle "<detalle>" y fecha "<fecha>"

    Examples:
      | tipo        | detalle                        | fecha               |
      | Cita médica | Consulta con endocrinólogo     | 2025-04-26 09:00am  |
      | Recordatorio| Tomar Metformina 500mg         | 2025-04-26 08:00am  |
      | Cita médica | Control de glucosa             | 2025-04-27 15:30pm  |

  Scenario: Sin próximas citas
    Given el paciente está autenticado
    When accede a "Próximas citas"
    Then el sistema muestra "No tienes citas próximas"
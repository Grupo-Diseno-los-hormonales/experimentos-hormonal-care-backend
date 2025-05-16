Feature: Ver calendario paciente
  Como paciente
  Quiero ver mi calendario con citas y medicación
  Para organizarme en mi tratamiento

  Scenario Outline: Visualizar calendario con eventos
    Given el paciente está autenticado
    When accede a "Calendario"
    Then el sistema muestra el evento "<tipo>" con detalle "<detalle>" y hora "<hora>"

    Examples:
      | tipo        | detalle                      | hora     |
      | Cita médica | Consulta con endocrinólogo   | 09:00am  |
      | Medicación  | Tomar Metformina 500mg       | 08:00am  |
      | Cita médica | Control de glucosa           | 15:30pm  |

  Scenario: Sin eventos programados
    Given el paciente está autenticado
    When accede a "Calendario"
    Then el sistema muestra "No hay eventos programados"
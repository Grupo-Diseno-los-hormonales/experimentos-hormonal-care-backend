Feature: Visualización de perfil del paciente/médico
  Como usuario
  Quiero acceder a los detalles de mi perfil
  Para mantenerme informado sobre mis datos registrados

  Scenario Outline: Visualizar perfil completo
    Given el usuario está autenticado
    When accede a "Perfil"
    Then el sistema muestra el perfil con nombre "<nombre>", rol "<rol>" y datos "<datos>"

    Examples:
      | nombre         | rol      | datos                                                        |
      | Carlos Perez   | Paciente | Edad: 34, Sangre: A+, Historial: Hipotiroidismo              |
      | Laura Martinez | Médico   | Especialidad: Endocrinología, CMP: 98765, Experiencia: 10 años|

  Scenario: Fallo en carga del perfil
    Given el usuario está autenticado
    When accede a "Perfil"
    Then el sistema muestra mensaje de error "No se pudo cargar el perfil"
Feature: Inicio de sesión con cuenta de HormonalCare
  Como usuario de "HormonalCare"
  Quiero iniciar sesión con mi cuenta registrada
  Para acceder a mis configuraciones de forma rápida y segura

  Scenario Outline: Inicio de sesión exitoso
    Given el usuario está en la pantalla de inicio de sesión
    When ingresa el correo "<correo>" y la contraseña "<contraseña>"
    And presiona el botón "Iniciar sesión"
    Then el sistema lo autentica y muestra la pantalla principal

    Examples:
      | correo             | contraseña    |
      | usuario@correo.com | Contraseña123 |

  Scenario Outline: Inicio de sesión fallido por credenciales inválidas
    Given el usuario está en la pantalla de inicio de sesión
    When ingresa el correo "<correo>" y la contraseña "<contraseña>"
    And presiona el botón "Iniciar sesión"
    Then el sistema muestra un mensaje de error "Correo o contraseña incorrectos"

    Examples:
      | correo             | contraseña   |
      | usuario@correo.com | claveErrada  |
      | correo@noexiste.com| Pass1234     |

import jpype
import jpype.imports
from jpype.types import *
from jpype import JImplements, JOverride, java
import time

jpype.startJVM(classpath=[
    "./target/classes",
    "./target/dependency/*"
])

from carcassonneWS import CarcassonneGUI
@JImplements(CarcassonneGUI)
class pythonGUI():
    def __init__(self):
        pass
    
    @JOverride
    def update(self):
        print("Python GUI updated successfully!")


from carcassonneWS import CarcassonneClient

gui_instance = pythonGUI()
client = CarcassonneClient("127.0.0.1", 3000, "Remi", gui_instance)

try:
    print("Client connecté. Appuyez sur Ctrl+C pour quitter...")
    while True:
        time.sleep(1)
except KeyboardInterrupt:
    print("\nFermeture...")
    client.leave()
    jpype.shutdownJVM()
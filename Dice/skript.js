// Szene, Kamera und Renderer einrichten
const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(70, window.innerWidth / window.innerHeight, 0.1, 1000);

// Renderer erstellen und transparenten Hintergrund aktivieren
const renderer = new THREE.WebGLRenderer({ alpha: true });
renderer.setSize(window.innerWidth, window.innerHeight);
renderer.setClearColor(0x000000, 0); // Transparent
document.body.appendChild(renderer.domElement);

// Vergrößerter Würfel erstellen
const geometry = new THREE.BoxGeometry(5, 5, 5); // Größerer Würfel
const materials = [];

// Materialien für die Würfelseiten erstellen
for (let i = 1; i <= 6; i++) {
    const canvas = document.createElement('canvas');
    canvas.width = 256;
    canvas.height = 256;
    const context = canvas.getContext('2d');

    // Roter Hintergrund
    context.fillStyle = 'red';
    context.fillRect(0, 0, 256, 256);

    // Weiße Punkte je nach Augenzahl
    context.fillStyle = 'white';
    const dotPositions = [
        [],
        [[128, 128]],
        [[64, 64], [192, 192]],
        [[64, 64], [128, 128], [192, 192]],
        [[64, 64], [64, 192], [192, 64], [192, 192]],
        [[64, 64], [64, 192], [192, 64], [192, 192], [128, 128]],
        [[64, 64], [64, 192], [192, 64], [192, 192], [64, 128], [192, 128]]
    ];
    dotPositions[i].forEach(([x, y]) => {
        context.beginPath();
        context.arc(x, y, 20, 0, Math.PI * 2);
        context.fill();
    });

    const texture = new THREE.CanvasTexture(canvas);
    materials.push(new THREE.MeshBasicMaterial({ map: texture }));
}

const cube = new THREE.Mesh(geometry, materials);
scene.add(cube);

// Kamera positionieren
camera.position.z = 15; // Kamera weiter weg für größeren Würfel

// Würfel zu Beginn in einer 3D-Position rotieren
cube.rotation.x = Math.PI / 4; // 45 Grad nach vorne
cube.rotation.y = Math.PI / 4; // 45 Grad seitlich

// Animationslogik
let isRolling = false;

function rollDice() {
    if (isRolling) return; // Verhindert mehrfaches Würfeln während der Animation
    isRolling = true;

    // Zufällige Zahl generieren (1-6)
    const rolledNumber = Math.floor(Math.random() * 6) + 1;

    // Zielrotation basierend auf der geworfenen Zahl
    const targetRotations = {
        1: { x: Math.PI / 2, y: 0 },
        2: { x: 0, y: Math.PI },
        3: { x: -Math.PI / 2, y: 0 },
        4: { x: 0, y: -Math.PI / 2 },
        5: { x: 0, y: Math.PI / 2 },
        6: { x: 0, y: 0 }
    };

    const targetRotation = targetRotations[rolledNumber];

    // Zunächst mehrfache schnelle Drehung, dann abrupt stoppen
    const spinDuration = 3000; // Gesamtzeit für Animation (3 Sekunden)
    const startRotation = { x: cube.rotation.x, y: cube.rotation.y };
    const spinStart = Date.now();

    // Funktion für die Animation
    function spin() {
        const elapsed = Date.now() - spinStart;
        const progress = Math.min(elapsed / spinDuration, 1);

        // Mehrfache schnelle Drehungen zu Beginn, dann abrupt stoppen
        const randomRotationFactorX = Math.random() * 10 * Math.PI;
        const randomRotationFactorY = Math.random() * 10 * Math.PI;

        // Berechnung der Rotation
        cube.rotation.x = startRotation.x + progress * randomRotationFactorX;
        cube.rotation.y = startRotation.y + progress * randomRotationFactorY;

        // Wenn die Animation fast vorbei ist, stoppe den Würfel auf der geworfenen Zahl
        if (progress >= 1) {
            cube.rotation.x = targetRotation.x;
            cube.rotation.y = targetRotation.y;
            isRolling = false; // Animation abgeschlossen
        } else {
            requestAnimationFrame(spin);
        }
    }
    spin();

    console.log('Geworfene Zahl:', rolledNumber);
}

// Klick-Ereignis für das Würfeln
window.addEventListener('click', rollDice);

// Animationsloop
function animate() {
    requestAnimationFrame(animate);
    renderer.render(scene, camera);
}
animate();

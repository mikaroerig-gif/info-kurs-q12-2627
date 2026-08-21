# Was wird von Git ignoriert?

Die `.gitignore` Datei verhindert, dass bestimmte Dateien versehentlich ins Repository hochgeladen werden.

## Ausgeschlossene Dateien

- `.env` / `.env.*` / `*.key` / `*.pem` — Passwörter, API-Keys und Zertifikate (Sicherheitsrisiko)
- `*.o` / `*.exe` / `*.out` — Kompilierte C/C++ Dateien (können lokal neu erzeugt werden)
- `.idea/` / `.vscode/` — IDE-Einstellungen (sind bei jedem anders)
- `*.swp` / `*.swo` / `*~` — Temporäre Editor-Dateien
- `.DS_Store` / `Thumbs.db` — Automatisch erzeugte Dateien von macOS und Windows
- `__pycache__/` / `*.pyc` / `venv/` — Python-Cache und virtuelle Umgebungen
- `node_modules/` / `package-lock.json` — Node.js Abhängigkeiten (werden per `npm install` neu geladen)
- `*.log` — Log-Dateien

## Was wird NICHT ignoriert

- `.java` Dateien — euer Quellcode
- `.class` Dateien — kompilierte Java-Dateien
- `.md` Dateien — Dokumentation
- `.uml` Dateien — UML-Diagramme
- Alle anderen Projektdateien

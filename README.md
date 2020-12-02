# Systems Programming - Assignment 1

Created by: Gavin Hailey and Giovanni Thompson

### Status
All functions work properly. Messaging and drawing work as expected. All clients see the same thing updated instantly. A user joining late will recieve the fully updated chat and canvas.
### Usage
- Hub (Server):
  `gcc hcompress.c linkedList.c -o hcompress -Wall`

- Painter (Client):
  `./hcompress -e {filename}.txt`

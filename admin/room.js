const ROOM_LIST = document.getElementById("ROOM_LIST")
const SEARCH_INPUT = document.getElementById("SEARCH_INPUT")
const SEARCH_BTN = document.getElementById("SEARCH_BTN")
const LOGOUT = document.getElementById("LOGOUT")
const MENU_EL = document.getElementById("MENU")

const ROOMS = [
  { ID: "ROOM101", CAPACITY: 35, EQUIPMENT: "โปรเจคเตอร์ 1 เครื่อง ไมค์ 5 ตัว", STATUS: "ว่าง", IMG: "https://images.unsplash.com/photo-1521737604893-d14cc237f11d?auto=format&fit=crop&w=1200&q=80" },
  { ID: "ROOM102", CAPACITY: 9,  EQUIPMENT: "ทีวี 1 เครื่อง ไมค์ 3 ตัว", STATUS: "ปิดปรับปรุง", IMG: "https://images.unsplash.com/photo-1556761175-129418cb2dfe?auto=format&fit=crop&w=1200&q=80" },
  { ID: "ROOM103", CAPACITY: 16, EQUIPMENT: "ทีวี 1 เครื่อง ไมค์ 3 ตัว ลำโพง 5 ตัว โปรเจคเตอร์ 1 ตัว", STATUS: "ว่าง", IMG: "https://images.unsplash.com/photo-1503387762-592deb58ef4e?auto=format&fit=crop&w=1200&q=80" },
  { ID: "ROOM104", CAPACITY: 12, EQUIPMENT: "ทีวี 1 เครื่อง ไมค์ 3 ตัว Macbook 1 เครื่อง", STATUS: "ว่าง", IMG: "https://images.unsplash.com/photo-1524758631624-e2822e304c36?auto=format&fit=crop&w=1200&q=80" },
  { ID: "ROOM105", CAPACITY: 10, EQUIPMENT: "ทีวี 1 เครื่อง ไมค์ 3 ตัว", STATUS: "ว่าง", IMG: "https://images.unsplash.com/photo-1517502884422-41eaead166d4?auto=format&fit=crop&w=1200&q=80" }
]

function SET_ACTIVE_MENU() {
  const PATH = (location.pathname.split("/").pop() || "rooms.html").toLowerCase()
  const PAGE = PATH.includes("rooms") ? "rooms"
    : PATH.includes("bookings") ? "bookings"
    : PATH.includes("users") ? "users"
    : "admin"

  const ITEMS = MENU_EL.querySelectorAll(".MENU_BTN")
  ITEMS.forEach(A => A.classList.remove("ACTIVE"))
  const TARGET = MENU_EL.querySelector(`.MENU_BTN[data-page="${PAGE}"]`)
  if (TARGET) TARGET.classList.add("ACTIVE")
}

function RENDER_LIST(LIST) {
  ROOM_LIST.innerHTML = ""
  LIST.forEach((R) => {
    const CARD = document.createElement("div")
    CARD.className = "CARD"

    const IMG = document.createElement("img")
    IMG.className = "IMG"
    IMG.src = R.IMG
    IMG.alt = R.ID

    const INFO = document.createElement("div")
    INFO.className = "INFO"

    const TITLE = document.createElement("div")
    TITLE.className = "ROOM_TITLE"
    TITLE.textContent = R.ID

    const META = document.createElement("div")
    META.className = "META"
    META.innerHTML =
      `<div><b>จำนวน :</b> ${R.CAPACITY} คน</div>` +
      `<div><b>อุปกรณ์ :</b> ${R.EQUIPMENT}</div>` +
      `<div><b>สถานะห้อง :</b> ${R.STATUS}</div>`

    const ROW_BOTTOM = document.createElement("div")
    ROW_BOTTOM.className = "ROW_BOTTOM"

    const BTN = document.createElement("button")
    BTN.className = "BTN_MANAGE"
    BTN.type = "button"
    BTN.textContent = "จัดการ"
    BTN.addEventListener("click", () => {
      alert(`จัดการห้อง: ${R.ID}`)
    })

    ROW_BOTTOM.appendChild(BTN)
    INFO.appendChild(TITLE)
    INFO.appendChild(META)
    INFO.appendChild(ROW_BOTTOM)

    CARD.appendChild(IMG)
    CARD.appendChild(INFO)

    ROOM_LIST.appendChild(CARD)
  })
}

function DO_SEARCH() {
  const Q = (SEARCH_INPUT.value || "").trim().toUpperCase()
  if (!Q) {
    RENDER_LIST(ROOMS)
    return
  }
  const FILTERED = ROOMS.filter((R) => {
    const A = R.ID.toUpperCase().includes(Q)
    const B = String(R.CAPACITY).includes(Q)
    const C = R.EQUIPMENT.toUpperCase().includes(Q)
    const D = R.STATUS.toUpperCase().includes(Q)
    return A || B || C || D
  })
  RENDER_LIST(FILTERED)
}

SEARCH_BTN.addEventListener("click", DO_SEARCH)
SEARCH_INPUT.addEventListener("keydown", (E) => {
  if (E.key === "Enter") DO_SEARCH()
})
LOGOUT.addEventListener("click", () => alert("Logout"))

SET_ACTIVE_MENU()
RENDER_LIST(ROOMS)

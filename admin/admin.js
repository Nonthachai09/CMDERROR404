const CHART_EL = document.getElementById("CHART")
const MENU_EL = document.getElementById("MENU")
const SEARCH_INPUT = document.getElementById("SEARCH_INPUT")
const SEARCH_BTN = document.getElementById("SEARCH_BTN")
const LOGOUT_BTN = document.getElementById("LOGOUT")

const WEEK_DATA = [
  { DAY: "M", VALUE: 2 },
  { DAY: "T", VALUE: 4 },
  { DAY: "W", VALUE: 5 },
  { DAY: "T", VALUE: 2 },
  { DAY: "F", VALUE: 4 },
  { DAY: "S", VALUE: 2 },
  { DAY: "S", VALUE: 3 }
]

const MAX_VALUE = Math.max(...WEEK_DATA.map(I => I.VALUE))
const MAX_BAR_PX = 160

function SET_ACTIVE_MENU() {
  const PATH = (location.pathname.split("/").pop() || "admin.html").toLowerCase()
  const PAGE = PATH.includes("rooms") ? "rooms"
    : PATH.includes("bookings") ? "bookings"
    : PATH.includes("users") ? "users"
    : "admin"

  const ITEMS = MENU_EL.querySelectorAll(".MENU_BTN")
  ITEMS.forEach(A => A.classList.remove("ACTIVE"))
  const TARGET = MENU_EL.querySelector(`.MENU_BTN[data-page="${PAGE}"]`)
  if (TARGET) TARGET.classList.add("ACTIVE")
}

function RENDER_CHART() {
  CHART_EL.innerHTML = ""
  WEEK_DATA.forEach((ITEM) => {
    const COL = document.createElement("div")
    COL.className = "BARCOL"

    const VAL = document.createElement("div")
    VAL.className = "VAL"
    VAL.textContent = String(ITEM.VALUE)

    const BAR = document.createElement("div")
    BAR.className = "BAR"
    const H = MAX_VALUE === 0 ? 10 : Math.max(10, Math.round((ITEM.VALUE / MAX_VALUE) * MAX_BAR_PX))
    BAR.style.height = `${H}px`

    const DAY = document.createElement("div")
    DAY.className = "DAY"
    DAY.textContent = ITEM.DAY

    COL.appendChild(VAL)
    COL.appendChild(BAR)
    COL.appendChild(DAY)
    CHART_EL.appendChild(COL)
  })
}

function HANDLE_SEARCH() {
  const Q = (SEARCH_INPUT.value || "").trim()
  if (!Q) return
  alert(`ค้นหา: ${Q}`)
}

SEARCH_BTN.addEventListener("click", HANDLE_SEARCH)
SEARCH_INPUT.addEventListener("keydown", (E) => {
  if (E.key === "Enter") HANDLE_SEARCH()
})
LOGOUT_BTN.addEventListener("click", () => alert("Logout"))

SET_ACTIVE_MENU()
RENDER_CHART()

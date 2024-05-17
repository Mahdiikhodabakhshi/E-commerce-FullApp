export interface ContactUsPageable {
  content: ContactUsContent[]
  pageable: Pageable
  last: boolean
  totalPages: number
  totalElements: number
  first: boolean
  numberOfElements: number
  size: number
  number: number
  sort: Sort2
  empty: boolean
}

export interface ContactUsContent {
  id: number
  subject: string
  email: string
  message: string
  sendDate: string
}

export interface Pageable {
  pageNumber: number
  pageSize: number
  sort: Sort
  offset: number
  paged: boolean
  unpaged: boolean
}

export interface Sort {
  empty: boolean
  sorted: boolean
  unsorted: boolean
}

export interface Sort2 {
  empty: boolean
  sorted: boolean
  unsorted: boolean
}

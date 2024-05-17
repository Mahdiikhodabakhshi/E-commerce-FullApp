export interface HomePageable {
  content: Content[]
  pageable: Pageable
  last: boolean
  totalElements: number
  totalPages: number
  first: boolean
  numberOfElements: number
  size: number
  number: number
  sort: Sort2
  empty: boolean
}

export interface Content {
  id: number
  title: string
  description: string
  location: string
  price: number
  homeType: string
  uploadDate: string
  image: string
  imageType: string
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

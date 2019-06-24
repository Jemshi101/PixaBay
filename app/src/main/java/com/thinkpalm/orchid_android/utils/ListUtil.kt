package com.thinkpalm.orchid_android.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.thinkpalm.orchid_android.webservice.models.excursion.SubProductsImage
import com.thinkpalm.orchid_android.webservice.models.hotel.response.bookingReview.HotelImage
import com.thinkpalm.orchid_android.webservice.models.hotel.response.detail.Amenity
import com.thinkpalm.orchid_android.webservice.models.location.LocationData
import com.thinkpalm.orchid_android.webservice.models.masterData.NationalityBean
import com.thinkpalm.orchid_android.webservice.models.masterData.VehicleLocationBean
import com.thinkpalm.orchid_android.webservice.models.transfer.LstImage

/**
 * Created by Jemsheer K D on 19 November, 2018.
 * Package com.thinkpalm.orchid_android.utils
 * Project RoyalArabianTravels
 */
class ListUtil {

	fun <T> ArrayList<T>.isNonEmpty(): Boolean {
		return this != null && !this.isEmpty()
	}

	fun <T> List<T>.isNonEmpty(): Boolean {
		return this != null && !this.isEmpty()
	}

	/*fun <T> ArrayList<T>.lastItem(): T {
		return this[this.size - 1]
	}

	fun <T> List<T>.lastItem(): T {
		return this[this.size - 1]
	}*/

	companion object {

		@JvmStatic
		fun <T> ArrayList<T>.lastItem(): T {
			return this[this.size - 1]
		}

		@JvmStatic
		fun <T> List<T>.lastItem(): T {
			return this[this.size - 1]
		}


		@JvmStatic
		fun <T> getArrayList(tList: List<T>?): ArrayList<T> {

			val list = ArrayList<T>()
			for (t in getNonNullList(tList)) {
				list.add(t)
			}
			return list
		}

		@JvmStatic
		fun <T> getNonNullList(tList: ArrayList<T>?): ArrayList<T> {
			return tList ?: arrayListOf()
		}

		@JvmStatic
		fun <T> getNonNullList(tList: List<T>?): List<T> {
			return tList ?: listOf()
		}

		@JvmStatic
		fun <T> equals(tList1: ArrayList<T>, tList2: ArrayList<T>): Boolean {
			return tList1 == tList2
		}

		@JvmStatic
		fun <T> equals(tList1: List<T>, tList2: List<T>): Boolean {
			return tList1 == tList2
		}

		@JvmStatic
		fun <T> isEmpty(tList: List<T>?): Boolean {
			return tList == null || tList.isEmpty()
		}

		@JvmStatic
		fun <T> isEmpty(tList: ArrayList<T>?): Boolean {
			return tList == null || tList.isEmpty()
		}

		@JvmStatic
		fun <E> printList(list: ArrayList<E>, tag: String) {
			for (i in list.withIndex()) {
				println("$tag : Index: ${i.index}, Value : ${i.value}")
			}
		}

		@JvmStatic
		fun getAmenityList(amenityList: List<Amenity>): List<Amenity> {
			var list = arrayListOf<Amenity>()

			for (item in amenityList) {
				var amenity: Amenity = Amenity()
				amenity.amenity = item.amenity
				amenity.amenityXID = item.amenityXID
				list.add(amenity)
			}

			return list
		}

		@JvmStatic
		fun getExcursionImageList(productsImageList: List<SubProductsImage>): ArrayList<String> {
			var list = arrayListOf<String>()
			for (item in productsImageList) {
				list.add(item.image)
			}
			return list
		}

		@JvmStatic
		fun getTransferImageList(productsImageList: List<LstImage>): ArrayList<String> {
			var list = arrayListOf<String>()
			for (item in productsImageList) {
				list.add(item.thumbImage)
			}
			return list
		}

		@JvmStatic
		fun getLocationDataList(vehicleLocationList: List<VehicleLocationBean>): ArrayList<LocationData> {
			var list = arrayListOf<LocationData>()
			for (item in vehicleLocationList) {
				list.add(item.getLocationData())
			}
			return list
		}

		@JvmStatic
		fun getCancellationPolicyFromList(productsImageList: List<HotelImage>): ArrayList<String> {
			var list = arrayListOf<String>()
			for (item in productsImageList) {
				list.add(item.image)
			}
			return list
		}

		@JvmStatic
		fun getNationality(id: Int,
		                   nationalityList: LiveData<ArrayList<NationalityBean>>
		): LiveData<NationalityBean> {
			return Transformations.map(nationalityList) { input ->
				if (input != null) {
					for (nationalityBean in input) {
						if (nationalityBean.id == id) {
							return@map nationalityBean
						}
					}
				}
				return@map null
			}
		}
	}
}
